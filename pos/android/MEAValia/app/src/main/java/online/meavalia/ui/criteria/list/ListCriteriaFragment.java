package online.meavalia.ui.criteria.list;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ActionMode;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import online.meavalia.R;
import online.meavalia.databinding.ItemCriteriaBinding;
import online.meavalia.databinding.ListCriteriaFragmentBinding;
import online.meavalia.domain.model.Criteria;
import online.meavalia.domain.repository.CriteriaRepository;
import online.meavalia.infrastructure.repository.impl.CriteriaRepositoryImpl;
import online.meavalia.ui.assessment.AssessmentExecutionActivity;
import online.meavalia.ui.generic.AbstractCustomFragmentImpl;

public class ListCriteriaFragment extends AbstractCustomFragmentImpl {

    private TransformViewHolder transformViewHolder;
    private final CriteriaRepository criteriaRepository = new CriteriaRepositoryImpl();
    private ListCriteriaFragmentBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = ListCriteriaFragmentBinding.inflate(inflater, container, false);

        configureTitle();
        configureBackButton();
        configureFabButton();
        configureListView();

        return binding.getRoot();
    }

    private void configureTitle() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setTitle(R.string.list_of_criteria);
    }

    private void configureBackButton() {
        Objects.requireNonNull(getMainActivity().getSupportActionBar()).setDisplayHomeAsUpEnabled(false);
    }

    private void finishActionMode() {
        if (this.transformViewHolder != null)
            this.transformViewHolder.finishActionMode();
    }

    private void configureFabButton() {
        binding.fab.setOnClickListener(view -> {
            getNavController().navigate(R.id.nav_insert_criteria);
            finishActionMode();
        });
    }

    private void configureListView() {
        final ListAdapter<Criteria, TransformViewHolder> adapter = new TransformAdapter();
        final RecyclerView recyclerView = binding.itemCriteria;
        recyclerView.setAdapter(adapter);

        final ListCriteriaViewModel listCriteriaViewModel =
                new ViewModelProvider(this)
                        .get(ListCriteriaViewModel.class);

        listCriteriaViewModel.getCriterias().observe(getViewLifecycleOwner(), adapter::submitList);
    }

    @Override
    public int getNavHostFragmentId() {
        return R.id.nav_host_fragment_content_main;
    }

    private AppCompatActivity getMainActivity() {
        return ((AppCompatActivity) getActivity());
    }

    private class TransformAdapter extends ListAdapter<Criteria, TransformViewHolder> {

        private final List<Integer> drawables = Arrays.asList(
                R.drawable.avatar_1,
                R.drawable.avatar_2,
                R.drawable.avatar_3,
                R.drawable.avatar_4,
                R.drawable.avatar_5,
                R.drawable.avatar_6,
                R.drawable.avatar_7,
                R.drawable.avatar_8,
                R.drawable.avatar_9,
                R.drawable.avatar_10,
                R.drawable.avatar_11,
                R.drawable.avatar_12,
                R.drawable.avatar_13,
                R.drawable.avatar_14,
                R.drawable.avatar_15,
                R.drawable.avatar_16);

        protected TransformAdapter() {
            super(new DiffUtil.ItemCallback<Criteria>() {

                @Override
                public boolean areItemsTheSame(@NonNull final Criteria oldItem, @NonNull final Criteria newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull final Criteria oldItem, @NonNull final Criteria newItem) {
                    return oldItem.equals(newItem);
                }
            });
        }

        @NonNull
        @Override
        public TransformViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {
            final ItemCriteriaBinding binding = ItemCriteriaBinding.inflate(LayoutInflater.from(parent.getContext()));
            transformViewHolder = new TransformViewHolder(binding);
            return transformViewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull TransformViewHolder holder, int position) {
            holder.criteriaNameTextView.setText(getItem(position).getName());
            holder.sentenceTextView.setText(getItem(position).getSentence());
            holder.averageTextView.setText(String.valueOf(getItem(position).getAvg()));
            if (getItem(position).getAvg() == null)
                holder.averageTextView.setVisibility(View.GONE);
            holder.setCriteria(getItem(position));
            holder.imageView.setImageDrawable(
                    ResourcesCompat.getDrawable(holder.imageView.getResources(),
                            drawables.get(position),
                            null));
        }
    }

    private class TransformViewHolder extends RecyclerView.ViewHolder implements ActionMode.Callback {

        private ActionMode actionMode;
        private Criteria criteria;
        private final ImageView imageView;
        private final TextView criteriaNameTextView;
        private final TextView sentenceTextView;
        private final TextView averageTextView;

        public TransformViewHolder(final ItemCriteriaBinding binding) {
            super(binding.getRoot());
            imageView = binding.imageViewItemTransform;
            criteriaNameTextView = binding.criteriaNameTextView;
            sentenceTextView = binding.criteriaSentenceTextView;
            averageTextView = binding.averageTextView;
            binding.imageViewItemTransform.getRootView().setClickable(true);
            binding.imageViewItemTransform.getRootView()
                    .setOnClickListener(view -> {
                        getMainActivity().startSupportActionMode(this);
                        final String message = getString(R.string.criteria) + " " +
                                criteria.getName() + " " + getString(R.string.selected);
                        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show();
                    });
        }

        public void setCriteria(Criteria criteria) {
            this.criteria = criteria;
        }

        @Override
        public boolean onCreateActionMode(ActionMode actionMode, Menu menu) {
            final MenuInflater inflate = actionMode.getMenuInflater();
            inflate.inflate(R.menu.criteria_selected, menu);
            this.actionMode = actionMode;
            return true;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            if (item.getItemId() == R.id.menuItemUpdateCriteria) {
                final Bundle bundle = new Bundle();
                bundle.putSerializable("criteria", criteria);
                getNavController().navigate(R.id.nav_edit_criteria, bundle);
                mode.finish();
                return true;
            } else if (item.getItemId() == R.id.menuItemUpdateRemoveCriteria) {

                final AtomicBoolean confirmed = new AtomicBoolean(true);
                final DialogInterface.OnClickListener listener = (dialog, which) -> {
                    if(which == DialogInterface.BUTTON_POSITIVE){
                        Toast.makeText(requireActivity(), getString(R.string.criteria_removed), Toast.LENGTH_SHORT).show();
                        criteriaRepository.remove(criteria);
                        onResume();
                        mode.finish();
                    } else
                        confirmed.set(false);
                };

                confirmToRemove(listener);

                return confirmed.get();
            } else if (item.getItemId() == R.id.menuItemExecuteAssessment) {
                startAssessmentActivity(criteria);
                mode.finish();
                return true;
            }
            return false;
        }

        private void confirmToRemove(final DialogInterface.OnClickListener listener) {
            final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setTitle(R.string.confirmation_title);
            builder.setIcon(android.R.drawable.ic_dialog_alert);
            builder.setMessage(R.string.confirmation_message);
            builder.setPositiveButton(R.string.yes, listener);
            builder.setNegativeButton(R.string.no, listener);
            final AlertDialog alert = builder.create();
            alert.show();
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {

        }

        private void startAssessmentActivity(final Criteria criteria) {
            final Intent intent = new Intent(requireActivity(), AssessmentExecutionActivity.class);
            intent.putExtra("criteria", criteria);
            requireActivity().startActivity(intent);
        }

        public void finishActionMode() {
            if (actionMode != null)
                actionMode.finish();
        }
    }

    @Override
    public void onResume() {
        configureListView();
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}